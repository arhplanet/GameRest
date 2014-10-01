package no.arhplanet.game;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.OAuth2FlowGoogleBuilder;
import org.glassfish.jersey.client.oauth2.OAuth2Parameters;
import org.glassfish.jersey.client.oauth2.TokenResult;

@Path("oauth2")
public class AuthorizeResource {

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("authorize")
    public Response authorize(@QueryParam("code") String code, @QueryParam("state") String state) {
        final OAuth2CodeGrantFlow flow = SimpleOAuthService.getFlow();
        final TokenResult tokenResult = flow.finish(code, state);

        //SimpleOAuthService.setAccessToken(tokenResult.getAccessToken());
        final URI uri = UriBuilder.fromUri(uriInfo.getBaseUri()).path("myresource")
                .queryParam("access_token", tokenResult.getAccessToken())
                .queryParam("type", tokenResult.getTokenType())
                .queryParam("expires_in", tokenResult.getExpiresIn())
                .build();
        return Response.seeOther(uri).build();
    }

    public static Response doAuthorize(UriInfo info) {
        final String redirectURI = UriBuilder.fromUri(info.getBaseUri())
                .path("oauth2/authorize").build().toString();

        final OAuth2CodeGrantFlow flow = OAuth2ClientSupport.googleFlowBuilder(
                SimpleOAuthService.getClientIdentifier(),
                redirectURI,
                "https://accounts.google.com/o/oauth2/auth")
                .property(OAuth2CodeGrantFlow.Phase.AUTHORIZATION, OAuth2Parameters.SCOPE, "email")
                .prompt(OAuth2FlowGoogleBuilder.Prompt.CONSENT)
                .accessType(OAuth2FlowGoogleBuilder.AccessType.OFFLINE)
                .build();

        SimpleOAuthService.setFlow(flow);

        // start the flow
        final String googleAuthURI = flow.start();

        // redirect user to Google Authorization URI.
        return Response.seeOther(UriBuilder.fromUri(googleAuthURI).build()).build();

    }
}