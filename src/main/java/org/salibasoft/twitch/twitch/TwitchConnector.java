/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under the terms of the CPAL v1.0 license,
 * a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.salibasoft.twitch.twitch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.mule.api.annotations.ConnectionStrategy;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.oauth.OAuthProtected;
import org.salibasoft.twitch.twitch.strategy.TwitchOAuthStrategy;

import com.uprised.twitch4j.beans.FeaturedStream;
import com.uprised.twitch4j.beans.Game;
import com.uprised.twitch4j.beans.Stream;
import com.uprised.twitch4j.beans.Team;
import com.uprised.twitch4j.beans.User;
import com.uprised.twitch4j.beans.Video;
import com.uprised.twitch4j.utils.GameRestCalls;
import com.uprised.twitch4j.utils.StreamRestCalls;
import com.uprised.twitch4j.utils.TeamRestCalls;
import com.uprised.twitch4j.utils.UserRestCalls;
import com.uprised.twitch4j.utils.VideoRestCalls;
import com.uprised.twitch4j.utils.exceptions.ChannelNotFoundException;
import com.uprised.twitch4j.utils.exceptions.TeamNotFoundException;
import com.uprised.twitch4j.utils.exceptions.TwitchUnauthenticatedException;
import com.uprised.twitch4j.utils.exceptions.VideoNotFoundException;

/**
 * Anypoint Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="twitch", friendlyName="Twitch")
public class TwitchConnector {

    @ConnectionStrategy
    TwitchOAuthStrategy connectionStrategy;

    /*
     * Stream REST calls
     */

    @Processor
    public Stream getChannelStream(String channelName) throws ClientProtocolException, ChannelNotFoundException, URISyntaxException, IOException {
    	return StreamRestCalls.getChannelStream(channelName);
    }

    @Processor
    public List<FeaturedStream> getFeaturedStreams() throws ClientProtocolException, URISyntaxException, IOException {
    	return StreamRestCalls.getFeaturedStreams();
    }

    @Processor
    public List<Stream> getStreams(String game) throws ClientProtocolException, IOException, URISyntaxException {
    	return StreamRestCalls.getStreams(game);
    }
    
    /*
     * Game REST calls
     */

    @Processor
    public List<Game> getTopGames() throws ClientProtocolException, URISyntaxException, IOException {
    	return GameRestCalls.getTopGames();
    }
    
    /*
     * Team REST calls
     */
    
    @Processor
	public Team getTeam(String teamName) throws URISyntaxException, ClientProtocolException, IOException, TeamNotFoundException {
		return TeamRestCalls.getTeam(teamName);
	}

    @Processor
	public List<Team> getTeams() throws ClientProtocolException, IOException, URISyntaxException {
		return TeamRestCalls.getTeams();
	}
    
    /*
     * User REST calls
     */
    
    @Processor
	public User getUser(String username) throws URISyntaxException, IOException {
    	return UserRestCalls.getUser(username);
    }

    @OAuthProtected
    @Processor
    public User getSelf() throws ClientProtocolException, TwitchUnauthenticatedException, URISyntaxException, IOException {
    	return UserRestCalls.getSelf(getConnectionStrategy().getAccessToken());
    }
    
    /*
     * Video REST calls
     */
    
    @Processor
	public Video getVideo(String videoId) throws URISyntaxException, ClientProtocolException, IOException, VideoNotFoundException {
    	return VideoRestCalls.getVideo(videoId);
    }

    @Processor
	public List<Video> getChannelVideos(String channel) throws URISyntaxException, ClientProtocolException, IOException, VideoNotFoundException {
		return VideoRestCalls.getChannelVideos(channel);
	}
    
    
    public TwitchOAuthStrategy getConnectionStrategy() {
        return connectionStrategy;
    }

    public void setConnectionStrategy(TwitchOAuthStrategy connectionStrategy) {
        this.connectionStrategy = connectionStrategy;
    }

}