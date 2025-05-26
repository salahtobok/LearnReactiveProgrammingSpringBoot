package com.webcodein.lrpsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * MainController handles the root URL ("/") of the application.
 * Returns the name of the view to be rendered reactively.
 */
@Controller
public class MainController {

    /**
     * Handles GET requests to the root path ("/").
     * Returns the logical view name "home" wrapped in a {@link Mono}.
     *
     * @return a {@link Mono} emitting the name of the view ("home")
     */
    @GetMapping("/")
    public Mono<String> handleMain() {
        return Mono.just("home"); // Assumes a template named "home.html" exists (e.g., Thymeleaf)
    }
}
