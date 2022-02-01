export async function postData(url = '', data = {}) {
  const fecthApi = urlApi(url)

  const response = await fetch(fecthApi, {
    method: 'POST',
    mode: 'cors',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data),
  })

  return response.json()
}

export const getData = async (url = '') => {
  const fecthApi = urlApi(url)

  const response = await fetch(fecthApi, {
    method: 'GET',
    mode: 'cors',
    headers: {
      'Content-Type': 'application/json',
    },
  })
  return response.json()
}

const urlApi = url => {
  return process.env.NODE_ENV === 'production'
    ? `https://eventos-unicauca.herokuapp.com/${url}`
    : `http://localhost:8080/${url}`
}
