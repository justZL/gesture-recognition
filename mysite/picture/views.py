from django.shortcuts import render
from django.http import JsonResponse

def upload_image_view(request):
    if request.method == 'POST' and request.FILES.get('image'):
        image_file = request.FILES['image']
        # 在此处执行您的图像处理逻辑
        # 例如，保存图像到特定位置、调用机器学习模型等
        return JsonResponse({'success': True, 'message': 'Image uploaded successfully'})
    else:
        return JsonResponse({'success': False, 'message': 'Invalid request'})
