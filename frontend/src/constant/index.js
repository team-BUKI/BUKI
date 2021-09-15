const keys = require("@/constant/key");

const API_SERVER_URL = keys.apiBaseUri;

const API_CLIENT_URL = keys.apiClientUri;

const OAUTH2_REDIRECT_URI = keys.redirectUri;

const GOOGLE_AUTH_URL = API_SERVER_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;

module.exports = {
    GOOGLE_AUTH_URL,
    API_SERVER_URL,
    API_CLIENT_URL
}