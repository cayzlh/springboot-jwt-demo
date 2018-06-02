package com.cayzlh.jwt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :
 *
 * <p></p>
 *
 * @author Ant丶
 * @date 2018-04-27.
 */
@RestController
@RequestMapping(value = "/jwt")
public class TestController {

	@RequestMapping(value = "/test")
	public String restTest() {
		return "hello .";
	}

}
