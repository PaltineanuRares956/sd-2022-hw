import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allUsers() {
    return HTTP.get(BASE_URL + "/users", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(user) {
    return HTTP.post(BASE_URL + "/users", user, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  update(user) {
    return HTTP.put(BASE_URL + "/users/" + user.id, user, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  delete(user) {
    return HTTP.delete(BASE_URL + "/users/" + user.id, {
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  },
  generateReport(type){
    return HTTP.post(BASE_URL + "users/export/" + type,{
      headers: authHeader(),
    }).then((response) => {
      return response.data;
    });
  }
};
