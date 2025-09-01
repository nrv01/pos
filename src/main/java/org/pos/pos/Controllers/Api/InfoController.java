package org.pos.pos.Controllers.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
@RequestMapping("info")
class InfoController {
    @GetMapping
    public Map<String, String> getApiInfo() {
        return Map.of(
                "name", "POS API",
                "version", "1.0.0"
        );
    }
}
