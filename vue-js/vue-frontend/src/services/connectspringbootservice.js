import axios from 'axios'


const SPRING_BOOT_EXAMPLE_URL = 'http://localhost:8080/api/testspring'

class ExampleService{
    getExampleService(){
        return axios.get(SPRING_BOOT_EXAMPLE_URL);
    }
}

export default new ExampleService()