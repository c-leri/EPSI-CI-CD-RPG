import { expect, test } from '@playwright/test';

test('home page has expected h1', async ({ page }) => {
	await page.goto('/');
	await expect(page.locator('h1').first()).toBeVisible();
});

test('Click "Charger une sauvegarde" shows modal with correct title and 5 buttons', async ({ page }) => {
	await page.goto('http://localhost:3000');


	await page.getByRole('button', { name: /Charger une sauvegarde/i }).click();


	await expect(page.getByText(/Choisissez votre numéro de sauvegarde/i)).toBeVisible();

	const buttons = await page.locator('button').all();
	expect(buttons).toHaveLength(5);
});


