import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allUsers() {
    return HTTP.get(BASE_URL + "/user", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(user) {
    return HTTP.post(BASE_URL + "/user", user, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(id, user){
    return HTTP.patch(BASE_URL + "/user/" + id, user, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  delete(id){
    return HTTP.delete(BASE_URL + "/user/" + id, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  addRegularTraining(userId, trainingId){
    return HTTP.patch(BASE_URL + "/user/" + userId + "/" + trainingId, { headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
    );
  },
}
;
