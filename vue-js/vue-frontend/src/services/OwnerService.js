import axios from "axios";
const SERVER_URL =
  "http://csi5450finalprojectbackend-env.eba-vdqznnev.us-east-2.elasticbeanstalk.com/";

class OwnerService {
  getAllowners() {
    return axios.get(SERVER_URL + "owner/");
  }

  create(owner) {
    return axios.post(SERVER_URL + "owner/add", owner);
  }

  getHomesbyownercity(ssn, city) {
    return axios.get(SERVER_URL + "homes/owner?owner=" + ssn + "&city=" + city);
  }

  update(owner) {
    return axios.put(SERVER_URL + "owner/update", owner);
  }
  getHomeownerbyID(id) {
    return axios.get(SERVER_URL + "owner/id/" + id);
  }

  getOwnerbyhometype(type) {
    return axios.post(SERVER_URL + "owner/homeType", type);
  }
}

export default new OwnerService();
