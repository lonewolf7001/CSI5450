import axios from 'axios'


const SPRING_BOOT_HOMES_URL = 'http://localhost:8081/homes/'

class HomeService{
    getAllhomes(){
        return axios.get(SPRING_BOOT_HOMES_URL);
    }
    create(home){
        return axios.post('http://localhost:8081/homes/add/address', home)
    }

    getAllcities(){
        return axios.get('http://localhost:8081/city/')
    }
    getHomesbycity(city){
        return axios.get('http://localhost:8081/homes/city?city='+city)
    }
    
    getHomesbyprice(price_min,price_max){
        return axios.get('http://localhost:8081/homes/price?min='+price_min+'&max='+price_max)
    }

    getAddressofhome(id){
        return axios.get('http://localhost:8081/address/home/'+id)
    }
// addAddresstonewhome(id, address){
    //     return axios.post('http://localhost:8081/address/id/'+id, address)
    // }
}

export default new HomeService();