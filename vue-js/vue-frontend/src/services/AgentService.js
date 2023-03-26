import axios from 'axios'


const SPRING_BOOT_AGENT_URL = 'http://localhost:8081/agent/'

class AgentService{
    getAllagents(){
        return axios.get(SPRING_BOOT_AGENT_URL);
    }
}

export default new AgentService();