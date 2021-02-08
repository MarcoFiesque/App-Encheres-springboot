package fr.eni.ecole.marc.encheres_eni.controllers;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.eni.ecole.marc.encheres_eni.entities.Article;
import fr.eni.ecole.marc.encheres_eni.entities.Categorie;
import fr.eni.ecole.marc.encheres_eni.entities.Role;
import fr.eni.ecole.marc.encheres_eni.entities.User;
import fr.eni.ecole.marc.encheres_eni.service.ArticleService;
import fr.eni.ecole.marc.encheres_eni.service.CategorieService;
import fr.eni.ecole.marc.encheres_eni.service.RoleService;
import fr.eni.ecole.marc.encheres_eni.service.UserService;

@Controller
public class AppController {
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CategorieService categorieService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	RoleService roleService;

	// Accueil
	
	@RequestMapping("/")
	public String listeArticlesV
		(
			ModelMap modelMap, 
			@RequestParam (name="page",defaultValue = "0") int page, 
			@RequestParam (name="size", defaultValue = "4") int size,
			Principal principal,
			Authentication auth,
			@Valid User user
		)
	{
		// Récuperation des pages d'articles
		Page<Article> pagesArticles = articleService.getAllArticlesParPage(page, size);
		// Nombre d'articles
		Long nbElementsPages = pagesArticles.getTotalElements();
		
		// Passage des pages d'articles en tant qu'attribut
		modelMap.addAttribute("pagesArticles",pagesArticles);
		modelMap.addAttribute("pages", new int[pagesArticles.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("nbElementsPages", nbElementsPages);
		modelMap.addAttribute("categories", categorieService.getAllCategories());
		System.out.println("Pages article :::::: " +pagesArticles);
		
		if(principal != null) {
			User userConnecte = userService.findByUsername(principal.getName());
			modelMap.addAttribute("user", userConnecte);
		}
		return "index";
	}
	
	// ArticleController
	
	@GetMapping("/createArticle")
	public String showCreate(ModelMap modelMap, @Valid Article article, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()){
			return "index";
		}
		modelMap.addAttribute("article", new Article());
		modelMap.addAttribute("categories", categorieService.getAllCategories());
		modelMap.addAttribute("mode", "create");

		return "formArticle";
	}
	
	@PostMapping("/saveArticle")
	public String saveArticle
						(
							@RequestParam("categorie") Long idCat, 
							@Valid Article article, Categorie categorie, 
							BindingResult bindingResult, ModelMap modelMap, 
							Principal principal,
							@RequestParam("fileImage") MultipartFile multipartFile,
							RedirectAttributes ra
							
						) throws IOException
	{
		// Récuperation des categories
		modelMap.addAttribute("mode", "create");
		modelMap.addAttribute("categories", categorieService.getAllCategories());
		
		User userConnecte = userService.findByUsername(principal.getName());

		if(bindingResult.hasErrors()) return "formArticle";
		/*
		// Récuperation de l'image soumise par le formulaire
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        
		article.setImageArticle(fileName);
        Article savedArticle = articleService.saveArticle(article);
		
        String uploadDir = "/article-images/" + savedArticle.getIdArticle();
        
        Path uploadPath = Paths.get(uploadDir);
        
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        
        ra.addFlashAttribute("success-message", "L'article a été ajouté");
        // */
		Categorie catChoisie = categorieService.getCategorie(idCat);
		article.setCategorie(catChoisie);
		article.setCreatedAt(LocalDateTime.now());
		article.setUser(userConnecte);
		
				
		articleService.saveArticle(article);
		return "formArticle";
	}
	
	@RequestMapping("/modifierArticle")
	public String editerArticle
							(
									//@ModelAttribute("article") Article article,
									@RequestParam("idArticle") Long idArticle,
									@RequestParam("categorieIdCat") Long idCat, 
									ModelMap modelMap, 
									Categorie categorie, 
									BindingResult bindingResult, 
									Principal principal,
									@RequestParam(name="page", defaultValue="0") int page,
								    @RequestParam(name="size", defaultValue="4") int size
							) 
	{
		modelMap.addAttribute("mode", "modif");
		User userConnecte = userService.findByUsername(principal.getName());
		
		Article article = articleService.getArticle(idArticle);
		
		modelMap.addAttribute("article", article);
		modelMap.addAttribute("categories", categorieService.getAllCategories());
		
		Categorie catChoisie = categorieService.getCategorie(idCat);
		article.setCategorie(catChoisie);
		article.setUpdatedAt(LocalDateTime.now());
			
			articleService.updateArticle(article);
			Page<Article> arts = articleService.getAllArticlesParPage(page, size);
			modelMap.addAttribute("articles",arts);
			return "formArticle";

	}
	/*
	@PostMapping("/updateArticle")
	public String updateArticle
			(
				@ModelAttribute("article") Article article, 
				@RequestParam("categorieIdCat") Long idCat, 
				ModelMap modelMap, 
				Categorie categorie, 
				BindingResult bindingResult, 
				Principal principal
			)
	throws ParseException
	{
			
		Categorie catChoisie = categorieService.getCategorie(idCat);
		article.setCategorie(catChoisie);
		article.setUpdatedAt(LocalDateTime.now());
			
			articleService.updateArticle(article);
			List<Article> articles = articleService.getAllArticles();
			modelMap.addAttribute("articles",articles);
			return "index";
			
	}
	*/
	
	@RequestMapping("/supprimerArticle")
	public String supprimerArticle(
			   @RequestParam("id") Long id, 
			   ModelMap modelMap, 
			   @RequestParam(name="page", defaultValue="0") int page,
			   @RequestParam(name="size", defaultValue="4") int size
			)
	{
		articleService.deleteArticleById(id);
		
		Page<Article> arts = articleService.getAllArticlesParPage(page, size);
		
		modelMap.addAttribute("pagesArticles",arts);
		modelMap.addAttribute("pages", new int[arts.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "index";
	}
	
	// UserController
	
	@RequestMapping("/register")
	public String showCreateUser(ModelMap modelMap) {
		modelMap.addAttribute("user", new User());
		modelMap.addAttribute("mode", "create");
		return "formUser";
	}
	
	@RequestMapping("/saveUser")
	public String saveUser(@Valid User user, BindingResult bindingResult, @RequestParam("verifPassword") String password, ModelMap modelMap)
	{
		if(bindingResult.hasErrors()) { return "formUser";}
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    String encodedVerifPassword = passwordEncoder.encode(password);
	    System.out.println("mode du formulaire : " + modelMap.getAttribute("mode"));
	    System.out.println(passwordEncoder.matches(password, encodedPassword));
	    if(passwordEncoder.matches(password, encodedPassword)) {
		    user.setPassword(encodedPassword);
		    user.setEnabled(true);
		    user.setCredit(100);
		    Set<Role> roles = user.getRoles();
			roles.add(roleService.findByNomRole("MEMBER"));
			userService.saveUser(user);
			System.out.println("user créé ! = " + user);
	    }
	    modelMap.addAttribute(user);
		return "redirect:/";
	}
	
	@RequestMapping("/modifierUser")
	public String editerUser
		(
			@RequestParam("id") Long id, 
			ModelMap modelMap,
			@Valid User user,
			BindingResult bindingResult
		) 
	{
		user = userService.getUser(id);
		modelMap.addAttribute("user", user);
		modelMap.addAttribute("mode", "modif");
		
		if(bindingResult.hasErrors()) {
			return "/formUser";
		}
		
		return "formUser";
	}
	
	@RequestMapping("/deleteUser")
	public String supprimerProduit
		(
			@RequestParam("id") Long id, 
			@RequestParam (name="page",defaultValue = "0") int page, 
			@RequestParam (name="size", defaultValue = "2") int size,
			RedirectAttributes redirAttrs,
			ModelMap modelMap
		)
	{
		userService.deleteByUserId(id);
		redirAttrs.addFlashAttribute("success", "Utilisateur supprimé !");
		return "redirect:/logout";
	}
	
	@RequestMapping("/showUtilisateurs")
	public String listeUtilisateurs
		(
			ModelMap modelMap, 
			@RequestParam (name="page",defaultValue = "0") int page, 
			@RequestParam (name="size", defaultValue = "4") int size,
			Principal principal
		)
	{
		if(principal != null) {
			User userConnecte = userService.findByUsername(principal.getName());
			modelMap.addAttribute("userConnecte", userConnecte);
			Long idUserConnecte = userConnecte.getUser_id();
			modelMap.addAttribute("idUserConnecte", idUserConnecte);
			System.out.println("Id utilisateur : " + idUserConnecte);
		}
		
		// Récuperation des pages d'articles
		Page<User> pagesUtilisateurs = userService.getAllUsersParPage(page, size);
		// Nombre d'articles
		Long nbElementsPages = pagesUtilisateurs.getTotalElements();

		// Passage des pages d'articles en tant qu'attribut
		modelMap.addAttribute("pagesUtilisateurs", pagesUtilisateurs);
		modelMap.addAttribute("pages", new int[pagesUtilisateurs.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("nbElementsPages", nbElementsPages);
		return "listeUtilisateurs";
	}
	
	
	@RequestMapping("/show_user")
	public String showUserProfile
		(
			Principal principal, 
			ModelMap modelMap,
			RedirectAttributes redirAttrs,
			@RequestParam("id") Long id
		)
	{
		
		User userConnecte = null;
		
		if(principal != null) {
			userConnecte = userService.findByUsername(principal.getName());
			modelMap.addAttribute("userConnecte", userConnecte);
		}
		
		User profilAffiche = userService.getUser(id);
		
		modelMap.addAttribute("profilAffiche", profilAffiche);
		
		if(userConnecte != null && userConnecte.getUser_id() == profilAffiche.getUser_id()) {
			profilAffiche = userConnecte;
			modelMap.addAttribute("owner", true);
			modelMap.addAttribute("mode", "modif");
		}else {
			modelMap.addAttribute("owner", false);
			modelMap.addAttribute("mode", "consult");
		}
		modelMap.addAttribute("user", profilAffiche);
		System.out.println("utilisateur courant : " + userConnecte);
		return "profil";
		
	}

	
	/*
	@RequestMapping("/supprimerProduit")
	public String supprimerProduit(@RequestParam("id") Long id, ModelMap modelMap, @RequestParam (name="page",defaultValue = "0") int page, @RequestParam (name="size", defaultValue = "2") int size)
	{
		articleService.deleteArticleById(id);
		Page<Article> prods = articleService.getAllArticlesParPage(page,size);
		modelMap.addAttribute("produits", prods); modelMap.addAttribute("pages", new int[prods.getTotalPages()]); modelMap.addAttribute("currentPage", page); modelMap.addAttribute("size", size);
		return "listeProduits";
	}
	*/
	/*
	public String saveArticle(@ModelAttribute("article") Article article, @RequestParam("date") String date, ModelMap modelMap) throws ParseException
	{
		// Conversion de la date
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateFormat.parse(String.valueOf(date));
		article.setDateCreation(dateCreation);
		
		Article saveArticle = articleService.saveArticle(article);
		String msg = "article enregistré avec l'Id " + saveArticle.getIdArticle();
		modelMap.addAttribute("msg", msg);
		return "createArticle";
	}
	*/

}
