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

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        productRepo.delete(id);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/addProduct",method = RequestMethod.GET)
    public String addProduct(){
        return "/addProduct";
    }

    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public ModelAndView create (@RequestParam("name")String name, @RequestParam("type")String type,
                                @RequestParam("price")double price){
        productRepo.save(new Product(name,type,price));
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("id") long id, @RequestParam("name")String name,
                               @RequestParam("type")String type, @RequestParam("price")double price) {
        Product product = productRepo.findOne(id);
        product.setName(name);
        product.setType(type);
        product.setPrice(price);
        productRepo.save(product);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        Product product = productRepo.findOne(id);
        model.addAttribute("product",product);
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
