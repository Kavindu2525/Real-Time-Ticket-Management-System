// src/components/ConfigurationForm.js
import React, { useState } from "react";
import { saveConfiguration } from "../services/ApiService";

const ConfigurationForm = () => {
  const [configuration, setConfiguration] = useState({
    maxTicketCapacity: "",
    totalTickets: "",
    releaseRate: "",
    retrievalRate: ""
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setConfiguration(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await saveConfiguration(configuration);
      alert("Configuration saved successfully!");
    } catch (error) {
      alert("Failed to save configuration");
    }
  };

  return (
    <div className="configuration-form">
      <h3>System Configuration</h3>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Max Ticket Capacity</label>
          <input
            type="number"
            name="maxTicketCapacity"
            value={configuration.maxTicketCapacity}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Total Tickets</label>
          <input
            type="number"
            name="totalTickets"
            value={configuration.totalTickets}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Release Rate</label>
          <input
            type="number"
            name="releaseRate"
            value={configuration.releaseRate}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Retrieval Rate</label>
          <input
            type="number"
            name="retrievalRate"
            value={configuration.retrievalRate}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Save Configuration</button>
      </form>
    </div>
  );
};

export default ConfigurationForm;
