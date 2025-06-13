import { useState } from "react";

export default function DataFilter() {
    const [data, setData] = useState([])
    const [formData, setFormData] = useState({
        id: "",
        job: "",
        status: "",
        type: "",
        jobClass: "",
        assembly: "",
        assemblyDescription: "",
        quantity: 0,
        quantityCompleted: 0,
        quantityScrapped: 0,
        quantityRemaining: 0,
        quantityMin: 0,
        quantityMax: 0,
        dateStart: "",
        dateEnd: "",
        startDate: "",
        dateCompleted: "",
        dateClosed: "",
        extraColumn: "",
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        console.log(formData)
        try {
            const response = await fetch(`http://localhost:8080/search?job=${formData.job}&type=${formData.type}&assembly=${formData.assembly}&assemblyDescription=${formData.assemblyDescription}&quantityMin=${formData.quantityMin}&quantityMax=${formData.quantityMax}&dateStart=${formData.dateStart}&dateEnd=${formData.dateEnd}`, {
                method: "GET"
            })
            const data = await response.json()
            setData(data)
            console.log("Datos recibidos")
        } catch (error) {

        }
    }

    return (
        <div className="min-h-screen bg-gray-900 text-white font-mono p-6">
            <div className="bg-gray-800 border border-cyan-400 p-6 max-w-4xl mx-auto rounded-xl shadow-[0_0_20px_5px_rgba(0,255,255,0.3)]">
                <h1 className="text-center text-cyan-400 text-xl mb-6 font-bold uppercase tracking-widest">
                    🔍 Filtrar Datos
                </h1>

                <section className="bg-gray-700 border border-purple-500 p-6 rounded-md shadow-[0_0_15px_rgba(128,0,255,0.5)] mb-4">
                    <h2 className="text-sm text-purple-400 mb-4 font-semibold uppercase tracking-wide">
                        Introduce los datos que quieras Filtrar
                    </h2>
                    <form onSubmit={handleSubmit}>
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="text"
                            name="job"
                            placeholder="Numero de orden"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="text"
                            name="type"
                            placeholder="Tipo de orden"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="text"
                            name="assembly"
                            placeholder="Numero de parte"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="text"
                            name="assemblyDescription"
                            placeholder="Descripcion numero de parte"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="text"
                            name="quantity"
                            placeholder="Cantidad surtida"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="text"
                            name="quantityScrapped"
                            placeholder="Cantidad Terminada"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="number"
                            name="quantityMin"
                            placeholder="Cantidad Minima"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="number"
                            name="quantityMax"
                            placeholder="Cantidad Maxima"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="date"
                            name="dateStart"
                            placeholder="Fecha de Inicio"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)] mb-2"
                            type="date"
                            name="dateEnd"
                            placeholder="Fecha de Termino"
                            onChange={handleChange}
                        />
                        <input
                            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)]"
                            type="text"
                            name="dateCompleted"
                            placeholder="Fecha completada"
                            onChange={handleChange}
                        />
                        <button
                            className="m-3 w-full bg-gradient-to-r from-green-400 via-lime-500 to-green-400 border border-green-300 text-black p-3 text-sm font-bold rounded-md hover:from-green-500 hover:to-lime-400 transition-all shadow-[0_0_10px_rgba(0,255,0,0.4)]"
                            type="submit"
                            onClick={() => handleSubmit}
                        >
                            📊 Filtrar Datos
                        </button>
                    </form>
                </section>


            </div>
        </div>
    );
}
