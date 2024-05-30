package com.sapient.employee.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.sapient.employee.entity.Role;

@Component
public class ServiceUtil {
	
	public List<String> getRolesAsString(Set<Role> set) {
		List<String> list = new ArrayList<>();
		set.forEach(role->{
			list.add(role.name());
		});
		return list;
	}
	
	public Set<Role> getRolesAsEnum(List<String> roles) {
		Set<Role> list = new HashSet<>();
		roles.forEach(role->{
			list.add(Role.valueOf(role));
		});
		return list;
	}
}
