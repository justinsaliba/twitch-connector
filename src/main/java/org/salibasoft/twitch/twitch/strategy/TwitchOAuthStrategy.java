/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.salibasoft.twitch.twitch.strategy;

import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.oauth.OAuth2;
import org.mule.api.annotations.oauth.OAuthAccessToken;
import org.mule.api.annotations.oauth.OAuthConsumerKey;
import org.mule.api.annotations.oauth.OAuthConsumerSecret;
import org.mule.api.annotations.oauth.OAuthScope;

/**
 * OAuth2 Connection Strategy
 *
 * @author MuleSoft, Inc.
 */
@OAuth2( configElementName = "oauth2-config", friendlyName="OAuth2 Configuration", authorizationUrl = "https://api.twitch.tv/kraken/oauth2/authorize", 
accessTokenUrl = "https://api.twitch.tv/kraken/oauth2/token", 
accessTokenRegex = "\"access_token\":\"([^&]+?)\"" )
public class TwitchOAuthStrategy
{
    /**
     * The OAuth access token
     */
    @OAuthAccessToken
    private String accessToken;
    
    /**
     * The OAuth consumer key
     */
    @Configurable
    @OAuthConsumerKey
    private String clientId;

    /**
     * The OAuth consumer secret
     */
    @Configurable
    @OAuthConsumerSecret
    private String clientSecret;
    
    @OAuthScope
    @Configurable
    private String scope;

    public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
     * Set accessToken
     *
     * @param accessToken
     *            The accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Get accessToken
     */
    public String getAccessToken() {
        return this.accessToken;
    }

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

}