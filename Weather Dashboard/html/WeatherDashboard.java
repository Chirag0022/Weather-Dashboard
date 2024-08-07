// Get the HTML elements to display the weather data
const cityElement = document.querySelector('#city');
const weatherDescriptionElement = document.querySelector('#weather-description');
const temperatureElement = document.querySelector('#temperature');
const humidityElement = document.querySelector('#humidity');
const windSpeedElement = document.querySelector('#wind-speed');
const forecastListElement = document.querySelector('#forecast-list');

// Function to display the current weather data
function displayCurrentWeather(weatherData) {
    cityElement.textContent = weatherData.name;
    weatherDescriptionElement.textContent = weatherData.weather[0].description;
    temperatureElement.textContent = `Temperature: ${weatherData.main.temp}°C`;
    humidityElement.textContent = `Humidity: ${weatherData.main.humidity}%`;
    windSpeedElement.textContent = `Wind Speed: ${weatherData.wind.speed} m/s`;
}

// Function to display the 5-day forecast data
function displayForecast(forecastData) {
    forecastListElement.innerHTML = '';
    forecastData.forEach((forecastDay) => {
        const forecastDayElement = document.createElement('div');
        forecastDayElement.classList.add('forecast-day');
        forecastDayElement.innerHTML = `
            <h3>${forecastDay.dt_txt}</h3>
            <p>Temperature: ${forecastDay.main.temp}°C</p>
            <p>Humidity: ${forecastDay.main.humidity}%</p>
            <p>Wind Speed: ${forecastDay.wind.speed} m/s</p>
        `;
        forecastListElement.appendChild(forecastDayElement);
    });
}

// Function to get the weather data from the API
function getWeatherData(city, countryCode, units) {
    const apiKey = '1234567890abcdef'; // Replace with your OpenWeatherMap API key
    const currentWeatherUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city},${countryCode}&units=${units}&appid=${apiKey}`;
    const forecastUrl = `https://api.openweathermap.org/data/2.5/forecast?q=${city},${countryCode}&units=${units}&appid=${apiKey}`;

    fetch(currentWeatherUrl)
        .then((response) => response.json())
        .then((weatherData) => displayCurrentWeather(weatherData))
        .catch((error) => console.error('Error fetching current weather data:', error));

    fetch(forecastUrl)
        .then((response) => response.json())
        .then((forecastData) => displayForecast(forecastData.list))
        .catch((error) => console.error('Error fetching forecast data:', error));
}

// Get the weather data for a specific city and country code
getWeatherData('London', 'uk', 'metric');