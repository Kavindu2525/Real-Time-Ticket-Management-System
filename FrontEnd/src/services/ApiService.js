// src/services/ApiService.js
import axios from "axios";
import { API_BASE_URL } from "../config";

export const saveConfiguration = async (data) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/configurations/save`, data);
    return response.data;
  } catch (error) {
    console.error("Error saving configuration:", error);
    throw error;
  }
};

export const addVendor = async (numberOfVendors) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/system/addVendor`, { numberOfVendors });
    return response.data;
  } catch (error) {
    console.error("Error adding vendor:", error);
    throw error;
  }
};

export const addCustomer = async (numberOfCustomers, quantity) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/system/addCustomer`, { 
      numberOfCustomers, 
      quantity 
    });
    return response.data;
  } catch (error) {
    console.error("Error adding customer:", error);
    throw error;
  }
};

export const startSystem = async () => {
  try {
    const response = await axios.post(`${API_BASE_URL}/system/start`);
    return response.data;
  } catch (error) {
    console.error("Error starting system:", error);
    throw error;
  }
};

export const stopSystem = async () => {
  try {
    const response = await axios.post(`${API_BASE_URL}/system/stop`);
    return response.data;
  } catch (error) {
    console.error("Error stopping system:", error);
    throw error;
  }
};
