import axios from 'axios'


const SPRING_BOOT_HOMES_URL = 'http://localhost:8081/homes/'

class HomeService{
    getAllhomes(){
        return axios.get(SPRING_BOOT_HOMES_URL);
    }
    create(home){
        return axios.post('http://localhost:8081/homes/add', home)
    }
}

export default new HomeService();