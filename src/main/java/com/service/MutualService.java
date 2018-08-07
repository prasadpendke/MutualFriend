package com.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.PersonalInfo;

@Component
public class MutualService {
	public List<PersonalInfo> allMutual(String id1, String id2) {
		List<PersonalInfo> myObjects = new ArrayList<>();
		List<PersonalInfo> mutualFrd = new ArrayList<>();
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();

		if (!id1.isEmpty() && !id2.isEmpty()) {
			try {
				ClassLoader classLoader = getClass().getClassLoader();
				File file = new File(classLoader.getResource("mutual.json").getFile());
				ObjectMapper objMapper = new ObjectMapper();

				myObjects = objMapper.readValue(file, new TypeReference<List<PersonalInfo>>(){});
				for (PersonalInfo p : myObjects) {
					if (id1.equals(p.getAccountId())) {
						l1 = new ArrayList(Arrays.asList((p.getFriendsList()).split(",")));
					} else if (id2.equals(p.getAccountId())) {
						l2 = new ArrayList(Arrays.asList((p.getFriendsList()).split(",")));
					}
				}
				
				l1.retainAll(l2);   // Take common accountId 
				
				//Now take objects for common accountId
				if (!l1.isEmpty()) {
					for (PersonalInfo p : myObjects) {
						if (l1.contains(p.getAccountId())) {
							mutualFrd.add(p);
						}
					}
				}

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return mutualFrd;
	}

}
