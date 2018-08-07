package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.PersonalInfo;
import com.service.MutualService;

@RestController
public class Controller {
	
	@Autowired
	MutualService ms;
	
	@RequestMapping(value="/mutual",method=RequestMethod.GET)
    public List<PersonalInfo> showMutual(@RequestParam("id1") String id1,@RequestParam(value="id2") String id2){
    	return ms.allMutual(id1,id2);
    }
	
}
