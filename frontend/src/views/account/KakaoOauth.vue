<template>
  <div>kakao</div>
</template>
<script>
import axios from "axios";
export default {
  name: "KakaoOauth",
  mounted() {
    const code = this.$route.query.code;
    console.log(code);
    const redirect_uri = "http://localhost:3000/kakao/oauth";

    document.cookie = "safeCookie1=foo; SameSite=Lax";
    document.cookie = "safeCookie2=foo";
    document.cookie = "crossCookie=bar; SameSite=None; Secure";

    const kakaoHeader = {
      Authorization: "23425c018b7547df7c7e46cbb9c7febf",
      "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
    };
    const getKakaoToken = async (code) => {
      console.log("loginWithKakao");
      try {
        const data = {
          grant_type: "authorization_code",
          client_id: "638d0a106bf1c98ba4b1fcf4f7b523a0",
          redirect_uri: redirect_uri,
          code: code,
        };
        const queryString = Object.keys(data)
          .map((k) => encodeURIComponent(k) + "=" + encodeURIComponent(data[k]))
          .join("&");
        const result = await axios.post("https://kauth.kakao.com/oauth/token", queryString, {
          headers: kakaoHeader,
        });
        console.log("카카오 토큰", result);
      } catch (e) {
        console.log(e);
      }
    };
    // axios({
    //   url: "https://kauth.kakao.com/oauth/token",
    //   method: "post",
    //   data: {
    //     grant_type: "authorization_code",
    //     client_id: client_id,
    //     redirect_uri: redirect_uri,
    //     code: code,
    //   },
    //   headers: {
    //     "Contetn-type": "application/x-www-form-urlencoded;charset=utf-8",
    //   },
    // }).then(({ data }) => {
    //   console.log(data);
    // });
  },
};
</script>
