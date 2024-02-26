package com.example.ProductManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/viewAllProductsPage")
    public String viewAllProductsPage(Model model)
    {
//        List<Product> Products = productService.getAllProducts();
//        model.addAttribute("Products",Products);
//        model.addAttribute("showLogout",true);
//        return "allProductsPage";
        return this.findPaginated(1,model);
    }

    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model)
    {
        Product product = new Product();
        model.addAttribute("product",product);
        return "newProductPage";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(Product product)
    {
        productService.addProduct(product);
        return "redirect:/viewAllProductsPage";
    }

    @GetMapping("/showUpdateProductForm/{id}")
    public String getProduct(@PathVariable int id, Model model)
    {
        Product product = productService.getProduct(id);
        model.addAttribute("product",product);
        return "updateProductPage";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id)
    {
        productService.deleteProduct(id);
        return "redirect:/viewAllProductsPage";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,Model model)
    {
        model.addAttribute("showLogout" , true);
        int pageSize = 3;
        Page<Product> page = productService.findPaginated(pageNo,pageSize);
        List<Product> Products = page.getContent();
        model.addAttribute("currentPage" , pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("Products",Products);
        return "allProductsPage";
    }
}
