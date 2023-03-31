import axios from 'axios'


const SPRING_BOOT_OWNER_URL = 'http://localhost:8081/owner/'

class OwnerService{
    getAllowners(){
        return axios.get(SPRING_BOOT_OWNER_URL);
    }

    create(owner){
        return axios.post('http://localhost:8081/owner/add', owner)
    }
    
    getHomesbyownercity(ssn,city){
        return axios.get('http://localhost:8081/homes/owner?owner='+ssn+'&city='+city)
    }
    


}

export default new OwnerService();