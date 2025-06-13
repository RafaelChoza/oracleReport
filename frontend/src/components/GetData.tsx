import { createContext, useContext, useEffect, useState } from "react";
import type { ItemOracleType } from "../Types";

interface DataContextType {
  info: ItemOracleType[];
}

const DataContext = createContext<DataContextType | undefined>(undefined);

export const DataProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [info, setInfo] = useState<ItemOracleType[]>([]);

  useEffect(() => {
    getDataDB();
  }, []);

  const getDataDB = async () => {
    try {
      const response = await fetch("http://localhost:8080/item");
      const data = await response.json();
      setInfo(data);
    } catch (error) {
      alert("Error con el servidor al querer recibir los datos");
    }
  };

  return <DataContext.Provider value={{ info }}>{children}</DataContext.Provider>;
};

export const useData = () => {
  const context = useContext(DataContext);
  if (!context) {
    throw new Error("useData debe usarse dentro de un DataProvider");
  }
  return context;
};
