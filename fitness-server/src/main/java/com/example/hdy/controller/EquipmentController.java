package com.example.hdy.controller;

import com.example.hdy.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    //返回所有器械
    @RequestMapping(value = "/equipment", method = RequestMethod.GET)
    public Object allEquipment(){
        return equipmentService.AllEquipment();
    }
    //返回器械func
    @RequestMapping(value = "/equipment/func/detail", method = RequestMethod.GET)
    public Object EquipmentOfFunc(HttpServletRequest req){
        String func = req.getParameter("func");
        System.out.println(func);
        return equipmentService.EquipmentOfFunc(func);
    }

}
