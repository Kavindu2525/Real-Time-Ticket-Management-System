// src/App.js
import React from "react";
import ConfigurationForm from "./components/ConfigurationForm";
import Controls from "./components/Controls";
import LogDisplay from "./components/LogDisplay";
import TicketAvailabilityDisplay from "./components/TicketAvailabilityDisplay";

const App = () => {
  return (
    <div className="ticket-system-app">
      <h1>Ticket Management System</h1>
      <div className="app-layout">
        <div className="left-panel">
          <ConfigurationForm />
          <Controls />
        </div>
        <div className="right-panel">
          <TicketAvailabilityDisplay />
          <LogDisplay />
        </div>
      </div>
    </div>
  );
};

export default App;