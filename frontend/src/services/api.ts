import axios from "axios";

import { OrderPayload } from "../pages/Orders/types";

//const API_URL = 'http://localhost:8080' test base localhost
const API_URL = "https://vbt-godelivery.herokuapp.com"

const mapboxToken = process.env.REACT_APP_ACCESS_TOKEN_MAP_BOX; /* Your token mapbox here! */

export const fetchProducts = () => {
    return axios(`${API_URL}/products`);
}

export const fetchLocalMapBox = (local: string) => {
    return axios(`https://api.mapbox.com/geocoding/v5/mapbox.places/${local}.json?access_token=${mapboxToken}`);
}

export const saveOrder = (payload: OrderPayload) => {
    return axios.post(`${API_URL}/orders`, payload);
}