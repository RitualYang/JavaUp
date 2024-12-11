package com.wty.network.ok;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoogleAnalytics4Request {

    /**
     * userid
     */
    private Long userId;

    private Map<String ,Map<String, Object>> events;

    private Map<String, Object> userProperties;

}
