import axios, { AxiosInstance } from 'axios';
import { AxiosRequestConfig, AxiosResponse, AxiosError } from 'axios';

export default class AxiosService {
    public static readonly instance: AxiosInstance = axios.create({
        baseURL: 'http://localhost:8081',
        timeout: 100000,
    });
}
