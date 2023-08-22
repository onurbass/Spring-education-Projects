package com.onurbas.web;

import com.onurbas.dto.CategoryDTO;
import com.onurbas.dto.request.PostRequestDTO;
import com.onurbas.dto.request.UserRequestDTO;
import com.onurbas.dto.response.PostResponseDTO;
import com.onurbas.dto.response.UserResponseDTO;
import com.onurbas.mapper.IPostMapper;
import com.onurbas.model.Category;
import com.onurbas.model.Post;
import com.onurbas.model.User;
import com.onurbas.service.CategoryService;
import com.onurbas.service.PostService;
import com.onurbas.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v2")
public class HomeController {

  private final UserService userService;
  private final PostService postService;
  private final CategoryService categoryService;
  public HomeController(UserService userService, PostService postService,CategoryService categoryService) {
	this.userService = userService;
	this.postService = postService;
	this.categoryService = categoryService;
  }
  @GetMapping("/api/v2")
  public String index(Model model) {
	List<UserResponseDTO> users = userService.findAll();
	List<PostResponseDTO> posts = postService.findAll();
	List<CategoryDTO> categories = categoryService.findAll();

	model.addAttribute("users", users);
	model.addAttribute("posts", posts);
	model.addAttribute("categories", categories);
	return "index";
  }

  @GetMapping("/users/new")
  public String newUser(Model model) {
	User user = new User();

	model.addAttribute("user", user);

	return "user-form";
  }

  @PostMapping("/users")
  public String saveUser(@ModelAttribute
						 UserRequestDTO user) {
	userService.save(user);

	return "redirect:/";
  }

  @GetMapping("/categories/new")
  public String newCategory(Model model) {
	Category category = new Category();

	model.addAttribute("category", category);

	return "category-form";
  }

  @PostMapping("/categories")
  public String saveCategory(
		  @ModelAttribute CategoryDTO categoryDTO) {
	categoryService.save(categoryDTO);

	return "redirect:/";
  }

  @GetMapping("/posts/new")
  public String newPost(Model model) {
	Post post = new Post();
	model.addAttribute("post", post);
	return "post-form";
  }
  @PostMapping("/posts")
  public String savePost(
		  @ModelAttribute PostRequestDTO postRequestDTO,
		  @RequestParam Long userId,
		  @RequestParam Long categoryId) {
	Post post = IPostMapper.INSTANCE.postRequestDTOToPost(postRequestDTO);
	post.setTitle(postRequestDTO.getTitle());
	post.setContent(postRequestDTO.getContent());
	post.setUser(userService.getById(userId));
	post.setCategory(categoryService.getById(categoryId));
	postService.save(IPostMapper.INSTANCE.postToPostRequestDTO(post),userId,categoryId);
	return "redirect:/";
  }
}