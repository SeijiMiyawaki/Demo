package com.example.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.Repository.UserRepository;
import com.example.entity.User;

@Controller
public class DemoController {

	@Autowired
	UserRepository repos;
	
	@RequestMapping("/")
	public ModelAndView index(@ModelAttribute("formModel") User user,ModelAndView mav) {
		mav.setViewName("index");
        Iterable<User> list = repos.findAll();
        mav.addObject("data", list);
        return mav;
	}
	
	@RequestMapping("/")
	@Transactional(readOnly=false)
	public ModelAndView form(@ModelAttribute("formModel") User user,ModelAndView mav) {
        repos.saveAndFlush(user);
        return new ModelAndView("redirect:/");
    }
	
	@PostConstruct
    public void init() {
        //初期データ作成
        User user1 = new User();
        user1.setName("兵庫　花子");
        repos.saveAndFlush(user1);

        user1 = new User();
        user1.setName("大阪　太郎");
        repos.saveAndFlush(user1);
    }
}
