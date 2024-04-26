import axios from 'axios';

export async function examAPI() {
    return await axios.get(`${process.env.REACT_APP_API_URL}`);
}
