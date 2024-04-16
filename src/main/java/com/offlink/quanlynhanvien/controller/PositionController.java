package com.offlink.quanlynhanvien.controller;

import com.offlink.quanlynhanvien.entity.Position;
import com.offlink.quanlynhanvien.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    public PositionService positionService;

    // injection
    public PositionController(PositionService thePositionService) {
        positionService = thePositionService;
    }

    // mapping list
    @RequestMapping("/list")
    public String listPosition(Model theModel, @RequestParam(required = false) String keyword) {
        theModel.addAttribute("positions", positionService.findAll());
        return "employees/list-positions";
    }

    //mapping showFormForAdd
    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Position position = new Position();
        theModel.addAttribute("position", position);
        return "employees/add-positions";
    }

    //mapping showFormForUpdate
    @RequestMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("positionId") long theId, Model theModel) {
        Position position = positionService.findPositionById(theId);
        theModel.addAttribute("position", position);
        return "employees/add-positions";
    }

    //mapping save
    @RequestMapping("/save")
    public String savePosition(@ModelAttribute("position") Position thePosition) {
        positionService.save(thePosition);
        return "redirect:/positions/list";
    }

    // mapping delete
    @RequestMapping("/delete")
    public String delete(@RequestParam("positionId") long theId) {
        positionService.deletedPositionById(theId);
        return "redirect:/positions/list";
    }

    // mapping search
    @RequestMapping("/search")
    public String searchPosition(@RequestParam("keyword") String keyword, Model theModel) {
        theModel.addAttribute("positions", positionService.searchPosition(keyword));
        return "employees/list-positions";
    }
}
