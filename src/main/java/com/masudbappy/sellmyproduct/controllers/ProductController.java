package com.masudbappy.sellmyproduct.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String productList(Model model){
        return "product/list";
    }
}
