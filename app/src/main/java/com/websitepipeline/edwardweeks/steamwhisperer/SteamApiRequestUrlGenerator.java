package com.websitepipeline.edwardweeks.steamwhisperer;

import java.util.Map;

public class SteamApiRequestUrlGenerator {

    private final String rootApiUrl = "http://api.steampowered.com/";
    private final String longVersion1 = "v0001";
    private final String longVersion2 = "v0002";
    private final String shortVersion1 = "v1";
    private final String shortVersion2 = "v2";
    private final String steamAppId = "2E0A2C9F0350C1BC0E59A151827A78DF";

    private String steamApiName;
    private String methodVersion;

    public SteamApiRequestUrlGenerator(){
        steamApiName = "";
        methodVersion = "";
    }

    public String generateRequest(String methodName, Map<String, String> arguments) {
        //TODO: should prob change shorthand to an enum
        String requestUrl;

        requestUrl = "";

        switch(methodName) {
            // ISteamNews Methods
            case "GetNewsForApp":
                methodVersion = longVersion2;
                steamApiName = "ISteamNews";
                break;

            // ISteamUserStats Methods
            case "GetGlobalAchievementPercentagesForApp":
                methodVersion = longVersion2;

            case "GetGlobalStatsForGame":
                methodVersion = longVersion1;

            case "GetPlayerAchievements":
                methodVersion = longVersion1;

            case "GetUserStatsForGame":
                methodVersion = longVersion2;

            case "GetSchemaForGame":
                methodVersion =  shortVersion2;
                steamApiName = "ISteamUserStats";
                break;

            // ISteamUser methods
            case "GetPlayerSummaries ":
                methodVersion = longVersion2;

            case "GetFriendList":
                methodVersion = longVersion1;

            case "GetPlayerBans":
                methodVersion = shortVersion1;
                steamApiName = "ISteamUser";
                break;

            // IPlayerService methods
            case "GetOwnedGames":
                methodVersion = longVersion1;

            case "GetRecentlyPlayedGames ":
                methodVersion = longVersion1;
            case "IsPlayingSharedGame":
                methodVersion = longVersion1;
                steamApiName = "IPlayerService";
                break;

            default:
                steamApiName = "ISteamUser";
                break;
        }

        requestUrl = rootApiUrl + steamApiName + "/" + methodName + "/" + methodVersion + "/";
        requestUrl += ("?format=json&key=" + steamAppId);

        for (Map.Entry<String, String> entry : arguments.entrySet()) {
            requestUrl+= "&" + entry.getKey() + "=" + entry.getValue();
        }

        return requestUrl;
    }

}
