package com.masudbappy.sellmyproduct.controllers;

import com.masudbappy.sellmyproduct.domain.Product;
import com.masudbappy.sellmyproduct.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DefaultController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/")
    public String home1(){
        return "/home";
    }

    @GetMapping("/home")
    public String home(){
        return "/home";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model){
        model.addAttribute("products",productRepo.findAll());
        return "/admin";
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    public String addProduct(){
        return "/addProduct";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("product")Product product,
                         BindingResult result, ModelMap modelMap){
        if (result.hasErrors()){
            return "error/403";
        }
        modelMap.addAttribute("name", product.getName());
        modelMap.addAttribute("type",product.getType());
        modelMap.addAttribute("price",product.getPrice());
        return "admin";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        productRepo.delete(id);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("post_id") long id,
                               @RequestParam("message") String message) {
        Product product = productRepo.findOne(id);

        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        return "/edit";
    }

    @GetMapping("/user")
    public String user(){
        return "/user";
    }

    @GetMapping("/about")
    public String about(){
        return "/about";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/403")
    public String error403(){
        return "/error/403";
    }



}
