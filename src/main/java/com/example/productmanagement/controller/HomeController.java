package com.example.productmanagement.controller;


import com.example.productmanagement.entity.Products;
import com.example.productmanagement.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/")
    public String home(Model model){

        List<Products> list = productRepo.findAll();
        model.addAttribute("all_products",list);

        return "index";
    }

    @GetMapping("/load_form")
    public String loadForm(){
        return "add";
    }

    @GetMapping("/edit_form/{id}")
    public String editForm(@PathVariable(value = "id") long id,Model model){

        Optional<Products> product=productRepo.findById(id);

        Products pro=product.get();
        model.addAttribute("product",pro);
        return "edit";
    }

    @PostMapping("/save_products")
    public String save_products(@ModelAttribute Products products, HttpSession session){
        productRepo.save(products);
        session.setAttribute("msg","Product added successfully");
        return "redirect:/load_form";
    }


}
