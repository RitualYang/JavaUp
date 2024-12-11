package com.wty.network.ok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleAnalytics4AppRequest extends GoogleAnalytics4Request {

    private String firebaseAppId;


}
