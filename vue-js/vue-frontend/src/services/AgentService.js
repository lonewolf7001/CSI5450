import axios from "axios";

const SERVER_URL =
  "http://csi5450finalprojectbackend-env.eba-vdqznnev.us-east-2.elasticbeanstalk.com/";

class AgentService {
  getAllagents() {
    return axios.get(SERVER_URL + "agent/");
  }

  create(agent) {
    return axios.post(SERVER_URL + "agent/add", agent);
  }

  update(agent) {
    return axios.put(SERVER_URL + "agent/update", agent);
  }
  getAllcompanies() {
    return axios.get(SERVER_URL + "company/");
  }
  getAgentofcompany(id) {
    return axios.get(SERVER_URL + "agent/company/" + id);
  }
  getAgentsdetails(id) {
    return axios.get(SERVER_URL + "agent/id/" + id);
  }
}

export default new AgentService();
