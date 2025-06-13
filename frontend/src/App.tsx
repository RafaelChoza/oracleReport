import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css'
import LoadCSV from './components/LoadCSV'
import DataFilter from './components/DataFilter'
import { DataProvider } from './components/GetData'


function App() {


  return (

    <BrowserRouter>
    <DataProvider>
      <Routes>
        <Route path='/loadCSV' element={<LoadCSV />} />
        <Route path='/dataFilter' element={<DataFilter/>} />
      </Routes>
    </DataProvider>
      
    </BrowserRouter>

  )
}

export default App
