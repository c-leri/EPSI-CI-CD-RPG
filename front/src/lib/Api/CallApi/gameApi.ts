import type { ApiResponse,Game } from '$lib/types/api';
import { API_CONFIG } from '$lib/Api/configapi/config';

export const gameApi = {
    async loadGame(id: number): Promise<ApiResponse<Game>> {
		console.log("loadGame called with id:", id);
		const response = await fetch(`${API_CONFIG.BACKEND_URL}/${API_CONFIG.ENDPOINTS.Game.gameGet.replace('{id}', id.toString())}`);
		

		const data: ApiResponse<Game> = await response.json();
        
		console.log("Response data:", data);
		return data;
	},

    async playNextRoom(id: number): Promise<ApiResponse<Game>> {
        console.log("playNextRoom called with id:", id);

        const response = await fetch(
            `${API_CONFIG.BACKEND_URL}/${API_CONFIG.ENDPOINTS.Game.gameNextRoom.replace('{id}', id.toString())}`,
            {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
            }
        );
		

		const data: ApiResponse<Game> = await response.json();
		console.log("Response data:", data);
		return data;
    }

}