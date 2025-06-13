import { useState } from "react";

const UploadCSV: React.FC = () => {
  const [file, setFile] = useState<File | null>(null);

  const handleUpload = async () => {
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    try {
      const response = await fetch("http://localhost:8080/api/csv/upload", {
        method: "POST",
        body: formData,
      });
      const data = await response.text();
      console.log(data);
    } catch (error) {
      console.error("Error al subir el archivo:", error);
    }
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white font-mono p-6">
      <div className="bg-gray-800 border border-cyan-400 p-6 max-w-4xl mx-auto rounded-xl shadow-[0_0_20px_5px_rgba(0,255,255,0.3)]">
        <h1 className="text-center text-cyan-400 text-xl mb-6 font-bold uppercase tracking-widest">
          📁 Subir archivo CSV
        </h1>

        <section className="bg-gray-700 border border-purple-500 p-6 rounded-md shadow-[0_0_15px_rgba(128,0,255,0.5)] mb-4">
          <h2 className="text-sm text-purple-400 mb-4 font-semibold uppercase tracking-wide">
            Selecciona un archivo
          </h2>
          <input
            className="w-full border border-cyan-500 p-2 bg-gray-800 text-cyan-300 text-sm rounded shadow-[inset_0_0_5px_rgba(0,255,255,0.3)]"
            type="file"
            accept=".csv"
            onChange={(e) => setFile(e.target.files?.[0] || null)}
          
          />
        </section>

        <button
          onClick={handleUpload}
          className="w-full bg-gradient-to-r from-green-400 via-lime-500 to-green-400 border border-green-300 text-black p-3 text-sm font-bold rounded-md hover:from-green-500 hover:to-lime-400 transition-all shadow-[0_0_10px_rgba(0,255,0,0.4)]"
        >
          🚀 Subir archivo
        </button>
      </div>
    </div>
  );
};

export default UploadCSV;
