package com.mb.api.web.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.mb.api.constants.GenericConstants.BASE_URL;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(BASE_URL)
public class BaseController
{

}
