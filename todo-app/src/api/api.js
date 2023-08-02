import axios from 'axios';

const API_URL = 'http://localhost:8080/api/v1/tasks';

const fetchTasks = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching tasks:', error);
        throw error;
    }
};

const createTask = async (title) => {
    try {
        const response = await axios.post(API_URL, { title });
        return response.data;
    } catch (error) {
        console.error('Error creating task:', error);
        throw error;
    }
};

const deleteTask = async (id) => {
    try {
        await axios.delete(`${API_URL}/${id}`);
    } catch (error) {
        console.error('Error deleting task:', error);
        throw error;
    }
};

const setTaskToDone = async (id) => {
    try {
        await axios.put(`${API_URL}/${id}`);
    } catch (error) {
        console.error('Error setting task to done:', error);
        throw error;
    }
};

export { fetchTasks, createTask, deleteTask, setTaskToDone };
