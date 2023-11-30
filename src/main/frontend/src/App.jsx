import { useState, useEffect } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";

function App() {
  // A quick reminder on how to access data via
  // react -> we need to use hooks! We will
  // usually use the useEffect hook in vanilla react
  // to render in data from an external source such
  // as an API (which we have running on our backend
  // in Springboot)
  const [customers, setCustomers] = useState([]);

  // Takes in 2 arguments:
  // 1. A callback that will be called either upon
  // first render or whenever a member of the dependency
  // array is updated
  // 2. A dependency array which is an array of objects, state,
  // callbacks, etc. that will trigger a rerun of the callback
  // whenever they change
  useEffect(() => {
    console.log("Rendered");
    fetch("http://localhost:8080/api/v2/customers")
      .then((res) => res.json())
      .then((data) => setCustomers(data));
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    const name = e.target.name.value;
    const favoriteProduct = e.target.favoriteProduct.value;
    const age = parseInt(e.target.age.value);
    const body = JSON.stringify({
      name: name,
      favoriteProduct: favoriteProduct,
      age: age,
    });

    fetch("http://localhost:8080/api/v2/customers", {
      body: body,
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      }
    })
  };

  return (
    <>
      {customers.map((customer) => {
        return (
          <div key={customer.id}>
            <h3>{customer.name}</h3>
            <p>Favorite Product: {customer.favoriteProduct}</p>
            <p>Age: {customer.age}</p>
          </div>
        );
      })}

      <h2>Add A New customer!</h2>
      <form onSubmit={handleSubmit}>
        <div style={{ display: "flex", flexDirection: "column", gap: "10px" }}>
          <input placeholder="Name" id="name" />
          <input placeholder="Favorite Product" id="favoriteProduct" />
          <input placeholder="Age" type="number" id="age" />
        </div>
        <div>
          <button>Submit</button>
        </div>
      </form>
    </>
  );
}

export default App;
