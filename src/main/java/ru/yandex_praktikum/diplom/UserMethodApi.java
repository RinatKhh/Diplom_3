package ru.yandex_praktikum.diplom;

import io.restassured.response.Response;

public class UserMethodApi extends ApiSetting {
    //создать пользователя
    public static Response createUser(User user) {
        return getSpec()
                .body(user)
                .when()
                .post(REGISTER);
    }
    //удалить пользователя по токену
    public  static void DeleteUser(String accessToken) {
        getSpec().auth()
                .oauth2(getAccessToken(accessToken))
                .delete(DELETE);
    }
    //залогиниться под пользователем
    public static Response SigInUser(User user) {
        return getSpec()
                .body(user)
                .when()
                .post(SIGNIN);
    }
}
