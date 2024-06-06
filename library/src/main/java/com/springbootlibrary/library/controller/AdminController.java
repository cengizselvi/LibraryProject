package com.springbootlibrary.library.controller;


import com.springbootlibrary.library.requestmodels.AddBookRequest;
import com.springbootlibrary.library.service.AdminService;
import com.springbootlibrary.library.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {


    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/secure/add/book")
    public void postBook(@RequestHeader(value = "Authorization") String token,
                         @RequestBody AddBookRequest addBookRequest) throws Exception {
        String admin= "cngzslv42@gmail.com";
        if (admin==null ) {
            throw new Exception("Admin değilsin");
        }
        adminService.postBook(addBookRequest);
    }


    @DeleteMapping("/secure/delete/book")
    public void deleteBook(@RequestHeader(value = "Authorization") String token,
                         @RequestParam Long bookId) throws Exception {
        String admin= "cngzslv42@gmail.com";
        if (admin==null ) {
            throw new Exception("Admin değilsin");
        }
        adminService.deleteBook(bookId);
    }
}
