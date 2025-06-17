import type { ApiResponse,NumberRoom } from '$lib/types/api';
import { API_CONFIG } from '$lib/Api/configapi/config';

export const examplecallApi = {
	async generateNew(): Promise<ApiResponse<NumberRoom>> {
		const response = await fetch(`${API_CONFIG.BACKEND_URL}${API_CONFIG.ENDPOINTS.TOTO.TOTO}`);
		const data: ApiResponse<NumberRoom> = await response.json();

		if (!response.ok) {
			throw new Error(data.message || 'Erreur réseau');
		}

		return data;
	}
};