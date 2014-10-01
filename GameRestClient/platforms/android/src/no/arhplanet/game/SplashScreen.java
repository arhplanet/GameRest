/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package no.arhplanet.game;

import org.apache.cordova.Config;
import org.apache.cordova.CordovaActivity;

import android.os.Bundle;

public class SplashScreen extends CordovaActivity
{

    String consumerKey = "296584509990-kpkn5h16ckhn9ntudu6vrvk40kpde77a.apps.googleusercontent.com";
    String consumerSecret = "813aHcCvbG2INLjPk0Thdrwt";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        /**
        ClientIdentifier identifier = new ClientIdentifier(consumerKey, consumerSecret);

        final OAuth2CodeGrantFlow flow = OAuth2ClientSupport.googleFlowBuilder(
                identifier,
                "urn:ietf:wg:oauth:2.0:oob",
                "https://accounts.google.com/o/oauth2/auth")
                .property(OAuth2CodeGrantFlow.Phase.AUTHORIZATION, OAuth2Parameters.SCOPE, "email")
                .prompt(OAuth2FlowGoogleBuilder.Prompt.CONSENT)
                .accessType(OAuth2FlowGoogleBuilder.AccessType.OFFLINE)
                .build();
        */

        super.onCreate(savedInstanceState);
        super.init();
        // Set by <content src="index.html" /> in config.xml
        super.loadUrl(Config.getStartUrl());
        //super.loadUrl("file:////www/login.html");
    }


}

